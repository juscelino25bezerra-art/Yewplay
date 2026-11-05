/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.local.yewplay

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.schabi.newpipe.R
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.local.subscription.SubscriptionsImportExportHelper
import org.schabi.newpipe.util.NavigationHelper

/** YewPlay: the ways to import subscriptions of the channel list, offered from other screens. */
object YpSubscriptionImport {

    /**
     * Asks where to import from: a NewPipe export file, or a service that can export its
     * subscriptions (YouTube, SoundCloud...), as the channel list menu does.
     *
     * @param fragment the screen asking
     * @param helper created by that screen in onCreate (it registers for activity results)
     * @param fragmentManager to open the import from a service
     */
    fun showChooser(
        fragment: Fragment,
        helper: SubscriptionsImportExportHelper,
        fragmentManager: FragmentManager
    ) {
        val context = fragment.requireContext()
        val options = mutableListOf<Pair<String, () -> Unit>>(
            context.getString(R.string.yp_subs_import_previous) to { helper.onImportPreviousSelected() }
        )
        for (service in ServiceList.all()) {
            val extractor = service.subscriptionExtractor ?: continue
            if (extractor.supportedSources.isEmpty()) {
                continue
            }
            options.add(
                service.serviceInfo.name to {
                    NavigationHelper.openSubscriptionsImportFragment(fragmentManager, service.serviceId)
                }
            )
        }
        AlertDialog.Builder(context)
            .setTitle(R.string.import_from)
            .setItems(options.map { it.first }.toTypedArray()) { _, which -> options[which].second() }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}
